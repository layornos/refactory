/**
 */
package org.eclipse.emf.modelSmells.smell_model.impl;


import org.eclipse.emf.common.notify.Notification;


import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.modelSmells.smell_model.ModelSmell;
import org.eclipse.emf.modelSmells.smell_model.Quality;
import org.eclipse.emf.modelSmells.smell_model.Quality_ModelSmell_Mapping;
import org.eclipse.emf.modelSmells.smell_model.Smell_modelPackage;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Quality Model Smell Mapping</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.modelSmells.smell_model.impl.Quality_ModelSmell_MappingImpl#getFactor <em>Factor</em>}</li>
 *   <li>{@link org.eclipse.emf.modelSmells.smell_model.impl.Quality_ModelSmell_MappingImpl#getModelSmell <em>Model Smell</em>}</li>
 *   <li>{@link org.eclipse.emf.modelSmells.smell_model.impl.Quality_ModelSmell_MappingImpl#getQuality <em>Quality</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class Quality_ModelSmell_MappingImpl extends EObjectImpl implements Quality_ModelSmell_Mapping {
	/**
	 * The default value of the '{@link #getFactor() <em>Factor</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFactor()
	 * @generated
	 * @ordered
	 */
	protected static final float FACTOR_EDEFAULT = 0.0F;

	/**
	 * The cached value of the '{@link #getFactor() <em>Factor</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFactor()
	 * @generated
	 * @ordered
	 */
	protected float factor = FACTOR_EDEFAULT;

	/**
	 * The cached value of the '{@link #getModelSmell() <em>Model Smell</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getModelSmell()
	 * @generated
	 * @ordered
	 */
	protected ModelSmell modelSmell;

	/**
	 * The cached value of the '{@link #getQuality() <em>Quality</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getQuality()
	 * @generated
	 * @ordered
	 */
	protected Quality quality;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	protected Quality_ModelSmell_MappingImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Smell_modelPackage.Literals.QUALITY_MODEL_SMELL_MAPPING;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public float getFactor() {
		return factor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFactor(float newFactor) {
		float oldFactor = factor;
		factor = newFactor;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Smell_modelPackage.QUALITY_MODEL_SMELL_MAPPING__FACTOR, oldFactor, factor));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ModelSmell getModelSmell() {
		if (modelSmell != null && modelSmell.eIsProxy()) {
			InternalEObject oldModelSmell = (InternalEObject)modelSmell;
			modelSmell = (ModelSmell)eResolveProxy(oldModelSmell);
			if (modelSmell != oldModelSmell) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, Smell_modelPackage.QUALITY_MODEL_SMELL_MAPPING__MODEL_SMELL, oldModelSmell, modelSmell));
			}
		}
		return modelSmell;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ModelSmell basicGetModelSmell() {
		return modelSmell;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setModelSmell(ModelSmell newModelSmell) {
		ModelSmell oldModelSmell = modelSmell;
		modelSmell = newModelSmell;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Smell_modelPackage.QUALITY_MODEL_SMELL_MAPPING__MODEL_SMELL, oldModelSmell, modelSmell));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Quality getQuality() {
		if (quality != null && quality.eIsProxy()) {
			InternalEObject oldQuality = (InternalEObject)quality;
			quality = (Quality)eResolveProxy(oldQuality);
			if (quality != oldQuality) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, Smell_modelPackage.QUALITY_MODEL_SMELL_MAPPING__QUALITY, oldQuality, quality));
			}
		}
		return quality;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Quality basicGetQuality() {
		return quality;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setQuality(Quality newQuality) {
		Quality oldQuality = quality;
		quality = newQuality;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Smell_modelPackage.QUALITY_MODEL_SMELL_MAPPING__QUALITY, oldQuality, quality));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case Smell_modelPackage.QUALITY_MODEL_SMELL_MAPPING__FACTOR:
				return getFactor();
			case Smell_modelPackage.QUALITY_MODEL_SMELL_MAPPING__MODEL_SMELL:
				if (resolve) return getModelSmell();
				return basicGetModelSmell();
			case Smell_modelPackage.QUALITY_MODEL_SMELL_MAPPING__QUALITY:
				if (resolve) return getQuality();
				return basicGetQuality();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case Smell_modelPackage.QUALITY_MODEL_SMELL_MAPPING__FACTOR:
				setFactor((Float)newValue);
				return;
			case Smell_modelPackage.QUALITY_MODEL_SMELL_MAPPING__MODEL_SMELL:
				setModelSmell((ModelSmell)newValue);
				return;
			case Smell_modelPackage.QUALITY_MODEL_SMELL_MAPPING__QUALITY:
				setQuality((Quality)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case Smell_modelPackage.QUALITY_MODEL_SMELL_MAPPING__FACTOR:
				setFactor(FACTOR_EDEFAULT);
				return;
			case Smell_modelPackage.QUALITY_MODEL_SMELL_MAPPING__MODEL_SMELL:
				setModelSmell((ModelSmell)null);
				return;
			case Smell_modelPackage.QUALITY_MODEL_SMELL_MAPPING__QUALITY:
				setQuality((Quality)null);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case Smell_modelPackage.QUALITY_MODEL_SMELL_MAPPING__FACTOR:
				return factor != FACTOR_EDEFAULT;
			case Smell_modelPackage.QUALITY_MODEL_SMELL_MAPPING__MODEL_SMELL:
				return modelSmell != null;
			case Smell_modelPackage.QUALITY_MODEL_SMELL_MAPPING__QUALITY:
				return quality != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (factor: ");
		result.append(factor);
		result.append(')');
		return result.toString();
	}

} //Quality_ModelSmell_MappingImpl
