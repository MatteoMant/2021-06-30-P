package it.polito.tdp.genes.model;

import java.util.HashSet;
import java.util.Set;

public class Adiacenza {
	
	private String v1;
	private String v2;
	private Set<String> tipi;
	// private int peso;
	
	public Adiacenza(String v1, String v2) {
		super();
		this.v1 = v1;
		this.v2 = v2;
		this.tipi = new HashSet<String>();
	}

	public String getV1() {
		return v1;
	}

	public void setV1(String v1) {
		this.v1 = v1;
	}

	public String getV2() {
		return v2;
	}

	public void setV2(String v2) {
		this.v2 = v2;
	}
	
	public void addTipo(String tipo) {
		this.tipi.add(tipo);
	}

	public Set<String> getTipi(){
		return this.tipi;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((v1 == null) ? 0 : v1.hashCode());
		result = prime * result + ((v2 == null) ? 0 : v2.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Adiacenza other = (Adiacenza) obj;
		
		return ((v1.equals(other.v1) && v2.equals(other.v2)) || (v1.equals(other.v2) && v2.equals(other.v1)));
		
	}
	
	
}
