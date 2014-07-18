package org.meruvian.yama.example.repository.category;

import org.meruvian.yama.repository.DefaultPersistence;

public interface Category extends DefaultPersistence {
	String getName();
	
	String getDescription();
	
	Category getParentCategory();
}
