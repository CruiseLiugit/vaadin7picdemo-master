package com.example.vaadindemo;

import com.example.vaadindemo.domain.Person;
import com.vaadin.annotations.Title;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

@Title("Vaadin Demo App")
public class VaadinApp extends UI {
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void init(VaadinRequest request) {
		
		HorizontalLayout hl = new HorizontalLayout();		
		
		//Property
		final Label helloLbl = new Label("Hello");
		//Button helloBtn = new Button(" Click ");
		final TextField helloTF = new TextField();
		
		String firstName = "Kazik";
		ObjectProperty<String> firstNameProp = new ObjectProperty<String>(firstName);
		
			//property data binding
		helloTF.setPropertyDataSource(firstNameProp); 
		helloLbl.setPropertyDataSource(firstNameProp);
		
		hl.addComponent(helloTF);
		hl.addComponent(helloLbl);
		
		helloTF.setImmediate(true);
		//---
		
		//Item
		Person person = new Person("Kazik", 1945);
		BeanItem<Person> personBean = new BeanItem<Person>(person);
		
		FormLayout formLayout = new FormLayout();
		FieldGroup fieldGroup = new FieldGroup(personBean);
		
		//fieldGroup.bindMemberFields(formLayout);
		//fieldGroup.buildAndBindMemberFields(formLayout);
		
		formLayout.addComponent(fieldGroup.buildAndBind("Name", "firstName"));
		formLayout.addComponent(fieldGroup.buildAndBind("YOB", "yob"));
		
		hl.addComponent(formLayout);
		//---
		
		//Container
		BeanItemContainer<Person> beanContainer = new BeanItemContainer<Person>(Person.class);
		beanContainer.addBean(new Person("Bolek", 1934));
		beanContainer.addBean(new Person("Lolek", 1933));
		
		final Table personTable = new Table();
		
			//container data binding
		personTable.setContainerDataSource(beanContainer);
		
		personTable.setSelectable(true);
		personTable.setImmediate(true);
		
		hl.addComponent(personTable);
		
		setContent(hl);
		
		personTable.addValueChangeListener(new Table.ValueChangeListener() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void valueChange(ValueChangeEvent event) {
				if(personTable.getValue() != null){
					Notification.show("Wybrano osobę " + personTable.getValue().toString());
				}
				
			}
		});
		//---
		
		//property data binding can be done this way
		/*helloBtn.addClickListener(new ClickListener() {

			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				//String message = helloTF.getValue();
				//lbl.setValue(message);			
			}
		});*/

	}

}
