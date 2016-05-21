package com.epam.vasilevsky.exchanger.webapp.page.course;

import org.apache.wicket.markup.html.link.Link;

import com.epam.vasilevsky.exchanger.datamodel.ExchangeRate;
import com.epam.vasilevsky.exchanger.webapp.page.AbstractHomePage;
import com.epam.vasilevsky.exchanger.webapp.page.course.panel.CourseListPanel;

public class CoursePage extends AbstractHomePage {

	@Override
	protected void onInitialize() {
		super.onInitialize();
		add(new CourseListPanel("list-panel"));
		
		add(new Link("create") {
          @Override
          public void onClick() {
              setResponsePage(new CourseEditPage(new ExchangeRate()));
          }
      });
	}

}
