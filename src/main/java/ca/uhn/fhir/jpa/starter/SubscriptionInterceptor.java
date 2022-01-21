package ca.uhn.fhir.jpa.starter;

import ca.uhn.fhir.interceptor.api.Hook;
import ca.uhn.fhir.interceptor.api.Interceptor;
import ca.uhn.fhir.interceptor.api.Pointcut;
import ca.uhn.fhir.rest.api.Constants;
import ca.uhn.fhir.rest.api.server.RequestDetails;
import org.hl7.fhir.r4.model.Patient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Interceptor
public class SubscriptionInterceptor {

	private static final Logger logger = LoggerFactory.getLogger(SubscriptionInterceptor.class);

	@Hook(Pointcut.STORAGE_PRESTORAGE_RESOURCE_CREATED)
	public void logIncomingResource(RequestDetails theRequest) {
		byte[] requestContents = theRequest.loadRequestContents();
		if (theRequest.getResource().getClass() == Patient.class) {
			String contents = new String(requestContents, Constants.CHARSET_UTF8);
			logger.info("Subscription interceptor received resource");
			logger.info(contents);
		}
	}
}
