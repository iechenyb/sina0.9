package com.cyb.spring;

import java.util.HashSet;
import java.util.Set;

public class BeanInformation {
	 private String id;
	    private String name;
	    private Set<PropertyInformation> propertyInformation = new HashSet<PropertyInformation>();
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public Set<PropertyInformation> getPropertyInformation() {
			return propertyInformation;
		}
		public void setPropertyInformation(Set<PropertyInformation> propertyInformation) {
			this.propertyInformation = propertyInformation;
		}
}
