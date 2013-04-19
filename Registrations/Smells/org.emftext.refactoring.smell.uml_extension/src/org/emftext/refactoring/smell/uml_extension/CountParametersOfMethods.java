package org.emftext.refactoring.smell.uml_extension;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.Operation;
import org.emftext.refactoring.smell.calculation.CalculationFactory;
import org.emftext.refactoring.smell.calculation.CalculationResult;
import org.emftext.refactoring.smell.calculation.Monotonicity;
import org.emftext.refactoring.smell.calculation.impl.MetricImpl;

public class CountParametersOfMethods extends MetricImpl {
	
	private float threshold;

	@Override
	public String getName() {
		return "Parameters Count";
	}

	@Override
	public Monotonicity getMonotonicity() {
		return Monotonicity.DECREASING;
	}

	@Override
	public String getDescription() {
		return "Determines those methods having a parameter count greater than the given threshold";
	}

	@Override
	public String getSmellMessage() {
		return "This parameter exceeds the defined maximum parameter count of " + threshold + ".";
	}

	@Override
	public CalculationResult calculate(EObject model, float threshold) {
		this.threshold = threshold;
		CalculationResult result = CalculationFactory.eINSTANCE.createCalculationResult();
		result.setResultingValue(0);
		if(model == null || !(model instanceof Model)){
			return null;
		}
		Model umlModel = (Model) model;
		for (Element element : umlModel.allOwnedElements()) {
			if(element instanceof Classifier){
				Classifier classifier = (Classifier) element;
				List<Operation> operations = classifier.getOperations();
				for (Operation operation : operations) {
					int parameterCount = operation.getOwnedParameters().size();
					if(parameterCount >= threshold){
						// -2 because a new parameter object is added thus it would be again more than the threshold
						for (int i = ((int)threshold - 2); i < parameterCount; i++) {
							result.getCausingObjects().add(operation.getOwnedParameters().get(i));
						}
						if(parameterCount > result.getResultingValue()){
							result.setResultingValue(parameterCount);
						}
					}
				}
			}
		}
		return result;
	}
}
