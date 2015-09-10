package com.vidya.db.utils;

import java.util.Set;

import javax.validation.ConstraintViolationException;
import javax.validation.ValidatorFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.stereotype.Component;

import com.mongodb.DBObject;

@Component
public class MongoDBSchemaValidator extends AbstractMongoEventListener
{
	private static final Logger LOG = LoggerFactory.getLogger(MongoDBSchemaValidator.class);

	@Autowired
	@Qualifier("validatorFactory")
	private ValidatorFactory validatorFactory;

	@Override
	public void onBeforeSave(Object source, DBObject dbo)
	{
		LOG.debug("Validating object: {}", source);
		Set violations = validatorFactory.getValidator().validate(source);

		if (!violations.isEmpty())
		{

			LOG.info("During object: {} validation violations found: {}", source, violations);
			throw new ConstraintViolationException(violations);
		}
	}
}
