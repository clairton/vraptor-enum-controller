# vraptor-enum-controller[![Build Status](https://drone.io/github.com/clairton/vraptor-enum-controller/status.png)](https://drone.io/github.com/clairton/vraptor-enum-controller/latest)

VRaptor Controller to retrieve enum values.

```java
public enum Contact{
  MAIN, ANOTHER
}
...
@Controller
@Path("contact")
public class ContactController extends EnumerableController<Contact> {

	@Deprecated
	public ContactController() {
		this(null, null);
	}

	@Inject
	public ContactController(final Result result, final Inflector inflector) {
		super(result, inflector, Contact.class);
	}
}
```

```http
URL              HTTP Method   Return
/contacts        [GET]         {contacts:['MAIN', 'ANOTHER']}
```

Se usar o maven, será necessário adicionar os repositórios:
```xml
<repository>
	<id>mvn-repo-releases</id>
	<url>https://raw.github.com/clairton/mvn-repo/releases</url>
</repository>
<repository>
	<id>mvn-repo-snapshot</id>
	<url>https://raw.github.com/clairton/mvn-repo/snapshots</url>
</repository>
```
 Também adicionar as depêndencias:
```xml
<dependency>
    <groupId>br.eti.clairton</groupId>
    <artifactId>vraptor-enum-controller</artifactId>
    <version>0.1.0</version><!--Ou versão mais recente-->
</dependency>
```
