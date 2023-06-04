package com.example.batchservice;

import org.slf4j.Logger;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import static org.slf4j.LoggerFactory.getLogger;

@Component
public class JobCompletionNotificationListener implements JobExecutionListener {

	private static final Logger log = getLogger( JobCompletionNotificationListener.class );

	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public JobCompletionNotificationListener( JdbcTemplate jdbcTemplate ) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void afterJob( JobExecution jobExecution ) {
		if ( jobExecution.getStatus() == BatchStatus.COMPLETED ) {
			log.info( "!!! JOB FINISHED! Time to verify the results" );

			jdbcTemplate.query( "SELECT first_name, last_name FROM people",
					( rs, row ) -> new Person(
							rs.getString( 1 ),
							rs.getString( 2 ) )
			).forEach( person -> log.info( "Found <{{}}> in the database.", person ) );
		}

	}
}
