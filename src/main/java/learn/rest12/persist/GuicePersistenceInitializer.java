package learn.rest12.persist;

import com.google.inject.Inject;
import com.google.inject.persist.PersistService;

public class GuicePersistenceInitializer {
	@Inject
	public GuicePersistenceInitializer(PersistService persistService) {
		persistService.start();
	}
}
