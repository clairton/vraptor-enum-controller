package br.eti.clairton;

import javax.inject.Inject;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.serialization.JSONSerialization;
import br.com.caelum.vraptor.serialization.Serialization;
import br.eti.clairton.inflector.Inflector;

/**
 * Controller Abstracto para recuperar o valor dos enums.
 * 
 * @author Clairton Rodrigo Heinzen<clairton.rodrigo@gmail.com>
 *
 * @param <T>
 *            tipo do enum
 */
public abstract class EnumerableController<T extends Enum<?>> {
	protected final Result result;
	protected final Inflector inflector;
	protected final Class<T> type;

	@Deprecated
	public EnumerableController() {
		this(null, null, null);
	}

	@Inject
	public EnumerableController(final Result result, final Inflector inflector, final Class<T> type) {
		this.result = result;
		this.type = type;
		this.inflector = inflector;
	}

	/**
	 * Retorna todos os Items do Enum.
	 */
	@Get
	public void index() {
		serialize();
	}

	protected void serialize() {
		final T[] values = getValues();
		final String tag = getTag();
		serializer(tag, values);
	}

	protected void serializer(final String tag, final T[] values) {
		result.use(serialization()).from(values, tag).serialize();
	}
	
	protected Class<? extends Serialization> serialization(){
		return JSONSerialization.class;
	}

	protected T[] getValues() {
		return type.getEnumConstants();
	}

	protected String getTag() {
		final String name = type.getSimpleName();
		return inflector.pluralize(inflector.uncapitalize(name));
	}
}
