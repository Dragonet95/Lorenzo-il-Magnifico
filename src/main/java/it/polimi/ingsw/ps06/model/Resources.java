package it.polimi.ingsw.ps06.model;

import java.util.EnumMap;
import java.util.Iterator;
import java.util.Set;

import it.polimi.ingsw.ps06.model.Types.MaterialsKind;
import it.polimi.ingsw.ps06.model.Types.PointsKind;

/**
* Classe per la modelizzazione delle risorse (materiale & punteggi)
*
* @author  ps06
* @version 1.1
* @since   2017-05-13
*/
public class Resources {
	
	private EnumMap<MaterialsKind, Integer> materials;
	private EnumMap<PointsKind, Integer> points;
	
	/**
	* Costruttore della classe. Inizializzazione delle Mappe dipendente dalla classe Enum di definizione
	*
	*/
	public Resources () {
		
		materials = new EnumMap<MaterialsKind, Integer>(MaterialsKind.class);
		points = new EnumMap<PointsKind, Integer>(PointsKind.class);
		
		for (MaterialsKind kind : MaterialsKind.values()) 	materials.put(kind, 0);
		for (PointsKind kind : PointsKind.values()) 		points.put(kind, 0);
	}
	
	public Resources (MaterialsKind kind, int value) {
		
		this();
		materials.put(kind, value);
	}
	
	public Resources (PointsKind kind, int value) {
		
		this();
		points.put(kind, value);
	}
	
	/**
	* Imposta un valore ad una certa risorsa di tipo materiale
	*
	* @param 	kind		Tipo di materiale a cui applicare l'operazione
	* @param	value		Valore da impostare al materiale
	* 
	* @return 	Nothing
	*/
	public void setMaterialsValue(MaterialsKind kind, int value) {
		materials.put(kind, value);
	}
	
	/**
	* Imposta un valore ad una certa risorsa di tipo punteggio
	*
	* @param 	kind		Tipo di punteggio a cui applicare l'operazione
	* @param	value		Valore da impostare al punteggio
	* 
	* @return 	Nothing
	*/
	public void setPointsValue(PointsKind kind, int value) {
		points.put(kind, value);
	}
	
	/**
	*	Pulisce tutte le risorse
	*
	* @return 	Nothing
	*/
	public void clearResources() {
		Set<MaterialsKind> materialsSet = materials.keySet();
        for(MaterialsKind currentMaterial : materialsSet) setResourceValue(currentMaterial, 0);
        
        Set<PointsKind> pointsSet = points.keySet();
        for(PointsKind currentPoint : pointsSet) setResourceValue(currentPoint, 0);

	}
	
	/**
	* Somma un valore ad una certa risorsa di tipo materiale (non distruttivo)
	*
	* @param 	kind			Tipo di materiale a cui applicare l'operazione
	* @param	increaseValue	Valore da impostare al materiale
	* 
	* @return 	Nothing
	*/
	public void increaseMaterialsValue(MaterialsKind kind, int increaseValue) {
		int currentValue = materials.get(kind);
		materials.put(kind, currentValue + increaseValue);
	}
	
	/**
	* Somma un valore ad una certa risorsa di tipo punteggio (non distruttivo)
	*
	* @param 	kind			Tipo di punteggio a cui applicare l'operazione
	* @param	increaseValue	Valore da impostare al punteggio
	* 
	* @return 	Nothing
	*/
	public void increasePointsValue(PointsKind kind, int increaseValue) {
		int currentValue = points.get(kind);
		points.put(kind, currentValue + increaseValue);
	}
	
	/**
	* Ottieni il valore corrente di una certa risorsa di tipo materiale
	*
	* @param 	kind			Tipo di materiale interessato
	* 
	* @return 	valore della risorsa 
	*/
	public int getMaterialsValue(MaterialsKind kind) {
		return (materials.get(kind));
	}
	
	/**
	* Ottieni il valore corrente di una certa risorsa di tipo punteggio
	*
	* @param 	kind			Tipo di punteggio interessato
	* 
	* @return 	valore della risorsa 
	*/
	public int getPointsValue(PointsKind kind) {
		return (points.get(kind));
	}

	/**
	* Somma la risorsa corrente con una seconda risorsa, settando correttamente
	* ogni singolo campo opportunatamente 
	*
	* @param 	r	Risorsa da sommare
	* 
	* @return 	Nothing. 
	*/
	public void add(Resources r) {
		
		Set<MaterialsKind> materialsSet = materials.keySet();
        for(MaterialsKind currentMaterial : materialsSet) increaseResourceValue(currentMaterial, r.getResourceValue(currentMaterial));
        
        Set<PointsKind> pointsSet = points.keySet();
        for(PointsKind currentPoint : pointsSet) increaseResourceValue(currentPoint, r.getResourceValue(currentPoint));
	}
	
	/**
	* Verifica se la risorsa corrente contiene una quantità di materiali e punteggi
	* uguale o maggiore rispetto ad una risorsa di controllo
	*
	* @param 	r		Risorsa di controllo per il confronto
	* 
	* @return 	true	se ogni risorsa corrente è almeno uguale alla risorsa confronto. 
	*/
	public boolean isBiggerThan(Resources r) {
		
		boolean flag = true;
		
		Iterator<MaterialsKind> materialsIterator = materials.keySet().iterator();
        while(materialsIterator.hasNext() && flag==true) 
        {
        	MaterialsKind currentMaterial = materialsIterator.next();	
        	if ( getResourceValue(currentMaterial) < r.getResourceValue(currentMaterial) ) flag = false;
        }

        Iterator<PointsKind> pointsIterator = points.keySet().iterator();
        while(materialsIterator.hasNext() && flag==true)
        {
        	PointsKind currentPoint = pointsIterator.next();	
        	if ( getResourceValue(currentPoint) < r.getResourceValue(currentPoint) ) flag = false;
        }
		
        return flag;
	}
}
