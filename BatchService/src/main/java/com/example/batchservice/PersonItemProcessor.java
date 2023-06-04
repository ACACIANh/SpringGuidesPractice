package com.example.batchservice;

import org.slf4j.Logger;
import org.springframework.batch.item.ItemProcessor;

import static org.slf4j.LoggerFactory.getLogger;

public class PersonItemProcessor implements ItemProcessor< Person, Person > {

	private static final Logger log = getLogger( PersonItemProcessor.class );

	@Override
	public Person process( final Person person ) throws Exception {
		final String firstName = person.getFirstName().toUpperCase();
		final String lastName = person.getLastName().toUpperCase();

		final Person transformedPerson = new Person( firstName, lastName );

		log.info( "Converting (" + person + ") into (" + transformedPerson + ")" );

		return transformedPerson;
	}
}
