package dk.itu.sdg.aspect.tracemodel.generator

import java.util.ArrayList
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.xtext.generator.IFileSystemAccess


class AspectGenerator implements IGenerator2 {
	
	private var String contents = ""
	
	override void doGenerate(Resource resource, IFileSystemAccess fsa) {
		//DO NOTHING
	}
	
	
	override void doGenerate(Iterable<?> resources, IFileSystemAccess fsa) {
		
		
		if (resources.size == 2) {
			
			var Resource resourceA = resources.toList.get(0) as Resource
			var Resource resourceB = resources.toList.get(1) as Resource
			
			
			var ArrayList<Pair> elementCombinations = getModelElementCombinations(resourceA.getNonAbstractModelElements(), resourceB.getNonAbstractModelElements())
			var int counter = 0		
						
			for (Pair elementCombination : elementCombinations) { 
				elementCombination.generatePointcutType1(counter)
				elementCombination.generatePointcutType2(counter)
				
				counter = counter + 1			
			}
			
			generateAdvise()
			
			counter = 0
			for (Pair elementCombination : elementCombinations) { 
				//generate advise
				elementCombination.generateAdvisePCType1(counter)
				elementCombination.generateAdvisePCType2(counter)
				
				counter = counter + 1			
			}
			
			
//			var EClass[] elements = resourceA.getNonAbstractModelElements() + resourceB.getNonAbstractModelElements()
//			for (EClass c : elements) {
//				c.generatePointcutType3()
//			}
			
			fsa.write()
			
		}
	}
	
	def generatePointcutType1(Pair p, int counter) {
		contents = contents + '''		
			private pointcut findMethodA«counter»(«p.fst.name» t1, «p.snd.name» t2) : !within(Tracer) && execution(* *(.., «p.fst.name», .., «p.snd.name», ..)) && args(t1,t2,..);
			private pointcut findMethodB«counter»(«p.snd.name» t1, «p.fst.name» t2) : !within(Tracer) && execution(* *(.., «p.snd.name», .., «p.fst.name», ..)) && args(t1,t2,..);
			
			
		''' 
	}

	def generatePointcutType2(Pair p, int counter) {
		contents = contents + '''		
			private pointcut findMethodC«counter»(«p.snd.name» t1) : !within(Tracer) && execution(«p.fst.name» *(.., «p.snd.name», ..)) && args(t1,..);
			private pointcut findMethodD«counter»(«p.fst.name» t1) : !within(Tracer) && execution(«p.snd.name» *(.. , «p.fst.name», ..)) && args(t1,..);

		''' 
	}
	
//	def generatePointcutType3(EClass e) {
//		contents = contents + '''			
//			private pointcut read«e.name»(«e.name» r) : !within(Tracer) && target(r) && call(* get*(..));
//			private pointcut write«e.name»(«e.name» w) : !within(Tracer) && target(w) && call(* set*(..));
//		''' 
//	}

	def generateAdvise() {
		contents = contents + '''
			//before(Object o) : belowMethod(o) {
			//	System.out.println("matched: " +  thisJoinPoint.getSignature());
			//}
		'''
	}
	
	def generateAdvisePCType1(Pair p, int counter) {
		contents = contents + '''
			//genertated by generateAdvisePCType1 first part
			
			Object tA«counter» = null, tB«counter» = null;
		'''
		generateSubAdvisePCType1PartA(p, counter, "A")
				
		contents = contents + '''
			//genertated by generateAdvisePCType1 second part			
		'''
		generateSubAdvisePCType1PartB(p, counter, "B")
	}
	
	def generateSubAdvisePCType1PartA(Pair p, int counter, String modifier) {
		contents = contents + '''
			after(«p.fst.name» t1, «p.snd.name» t2) : findMethod«modifier»«counter»(t1, t2) {
				if(!this.tA«counter».equals(t1) || !this.tB«counter».equals(t2)) {
					TraceCollector tc = TraceCollector.getInstance();
					tc.setTrace(t1, thisJoinPoint, t2);
					System.out.println("trace " + EcoreUtil.getURI((EObject)t1) + " <--> " + EcoreUtil.getURI((EObject)t2) + " by " +  thisJoinPoint.getSignature().toShortString() + " in " + thisJoinPoint.getSourceLocation().toString());
				}
			}
			
			before(«p.fst.name» t1, «p.snd.name» t2) : findMethod«modifier»«counter»(t1, t2) {
				
				//System.out.println("findMethod«modifier»«counter»: " +  thisJoinPoint.getSignature().toShortString());
				
				this.tA«counter» = EcoreUtil.copy((EObject)t1);
				this.tB«counter» = EcoreUtil.copy((EObject)t2);
			}
		'''
	}
	
	def generateSubAdvisePCType1PartB(Pair p, int counter, String modifier) {
		contents = contents + '''
			after(«p.snd.name» t1, «p.fst.name» t2) : findMethod«modifier»«counter»(t1, t2) {
				if(!this.tA«counter».equals(t1) || !this.tB«counter».equals(t2)) {
					TraceCollector tc = TraceCollector.getInstance();
					tc.setTrace(t1, thisJoinPoint, t2);
					System.out.println("trace " + EcoreUtil.getURI((EObject)t1) + " <--> " + EcoreUtil.getURI((EObject)t2) + " by " +  thisJoinPoint.getSignature().toShortString() + " in " + thisJoinPoint.getSourceLocation().toString());
				}
			}
			
			before(«p.snd.name» t1, «p.fst.name» t2) : findMethod«modifier»«counter»(t1, t2) {
				
				//System.out.println("findMethod«modifier»«counter»: " +  thisJoinPoint.getSignature().toShortString());
				
				this.tA«counter» = EcoreUtil.copy((EObject)t1);
				this.tB«counter» = EcoreUtil.copy((EObject)t2);
			}
		'''	
	}
	
	def generateAdvisePCType2(Pair p, int counter) {
		contents = contents + '''
			//genertated by generateAdvisePCType2 first part
		'''
		generateSubAdvisePCType2PartA(p, counter, "C")
		
		contents = contents + '''
			//genertated by generateAdvisePCType2 second part
		'''
		
		generateSubAdvisePCType2PartB(p, counter, "D")
	}
	
	def generateSubAdvisePCType2PartA(Pair p, int counter, String modifier) {
		contents = contents + '''
			after(«p.snd.name» t1) returning («p.fst.name» returnValue): findMethod«modifier»«counter»(t1) {		
				if(t1 != null && returnValue != null) {
					TraceCollector tc = TraceCollector.getInstance();
					tc.setDirectedTrace(t1, thisJoinPoint, returnValue);
					System.out.println("trace " + EcoreUtil.getURI((EObject)t1) + " --> " + EcoreUtil.getURI((EObject)returnValue) + " by " +  thisJoinPoint.getSignature().toShortString() + " in " + thisJoinPoint.getSourceLocation().toString());
				}
			}
		'''
	}
	
	def generateSubAdvisePCType2PartB(Pair p, int counter, String modifier) {
		contents = contents + '''
			after(«p.fst.name» t1) returning («p.snd.name» returnValue): findMethod«modifier»«counter»(t1) {				
				if(t1 != null && returnValue != null) {
					TraceCollector tc = TraceCollector.getInstance();
					tc.setDirectedTrace(t1, thisJoinPoint, returnValue);
					System.out.println("trace " + EcoreUtil.getURI((EObject)t1) + " --> " + EcoreUtil.getURI((EObject)returnValue) + " by " +  thisJoinPoint.getSignature().toShortString() + " in " + thisJoinPoint.getSourceLocation().toString());
				}
			}
		'''
	}
	
	def dispatch compile(EClass e, IFileSystemAccess fsa) {
		contents = contents
	}
	
	def dispatch compile(EObject e, IFileSystemAccess fsa) {
		contents = contents
	}
		
	def dispatch compile(Pair p, IFileSystemAccess fsa) {
		contents = contents
	}

	def write(IFileSystemAccess fsa) {
		fsa.generateFile("Tracer.aj", '''
package dk.itu.sdg.tracemodel.observer;

import java.util.ArrayList;
import org.aspectj.lang.JoinPoint;
import org.eclipse.emf.ecore.util.EcoreUtil;

import dk.itu.sdg.tracemodel.runtimedata.TraceCollector;


public aspect Tracer {
	
	//TODO: if scoping to a certain class is need fill it in here!!!
	//private pointcut belowMethod(Object o) : within(!!!Put class name here!!!) && !within(Tracer) && cflow(execution(* *(..))) && target(o);
	
''' + contents + '''
}''')
	}	



	//------------------------- HELPER METHODS -------------------------
	def EClass[] getNonAbstractModelElements(Resource model) {
		//get all eClasses
		var modelClasses = model.allContents.toIterable.filter(typeof(EClass)).filter(e | !e.isAbstract())			
		return modelClasses
	}
	
	def ArrayList<Pair> getModelElementCombinations(EClass[] modelAClasses, EClass[] modelBClasses) {
		
		var elementPairs = new ArrayList<Pair>() 
		for (EClass modelAClass : modelAClasses) {
			for (EClass modelBClass : modelBClasses) {
				var Pair pair = new Pair(modelAClass, modelBClass)
				elementPairs.add(pair)
			}	
		}
		
		return elementPairs
	}



}




