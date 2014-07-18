package org.meruvian.yama.example.repository.news;

import org.meruvian.yama.repository.DefaultPersistence;

public interface News extends DefaultPersistence {
	String getTitle();

	String getContent();

}
