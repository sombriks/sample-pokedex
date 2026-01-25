# Decisions

The study steps are registered here.

## Repository first

Created an empty github repository to receive the implementation.

## Some issues to guide development

Created issues to guide major steps. Others may be created during the
development process.

## Basic structure

Blank Spring boot project created with [spring tarter][initializr]. Several
empty files will help to guide future tasks.

I intend to develop further using branches to simulate a day to day operations. 

[initializr]: https://start.spring.io/#!type=maven-project&language=java&platformVersion=4.0.2&packaging=jar&configurationFileFormat=yaml&jvmVersion=25&groupId=sample.pokedex&artifactId=demo&name=restapi&description=Demo%20project%20for%20Spring%20Boot&packageName=sample.pokedex.restapi&dependencies=data-jpa,postgresql,liquibase,testcontainers,devtools,web,security,cache,data-redis

## List pokemon

Once i got base infrastructure, i decided to go for the simplest endpoint. I
chose this because i can study the pokedex api in the process.

### 'HATEOAS'

The listing endpoint in the pokedex api seems to be a [HATEOAS][hateoas] thing.

[hateoas]: https://htmx.org/essays/hateoas/

I don't agree 100% to call it that way, since there is JSON instead of
hypermedia, but i digress.

The key consequence here is that i'll need to perform several requests to
properly assemble the desired output, and it also explains why the backing api
client calls should be cached.

### Declarative REST Client

Over the various ways that spring boot offers to consume rest apis, i decided to
go as declarative as possible.

Initial mapping is as close as possible to the pokeapi but not mapping it completely. just the data i judge important, according to the requirements.
i'll use other services to map the pokeapi dtos to the desired payload.

### Swallow or rethrow exceptions?

I decided to swallow exceptions in the detail endpoint.
i could make a special case to avoid it, but i decided to not let backing api
errors (timeouts, bad requests, all the 5XX family as well) to compromise my clients.

Let's see how it evolves.

### DTOs 

All mapping is done by DTOs. Those records will know exactly what they need to
know and nothing more.

The pokeapi client records doesn't know nothing about the rest of the restapi,
but records from the rest api knows how to build themselfes from the client
records.

I's almost time to think about the rest of the details, but basic mappings and tests are fone.

## Pokemon Details

For pokemon details i'll need to augment the data with the pokemon description
and evolutions, so let's check the pokeapi again.

### A matter of hydration 

The backing api spreads the desired information over distinct endpoints. The
listing endpoint needs some hydration, but not the full extra requests as the
detail endpoint.

Therefore, the service layer has extra intelligence to decide the hydration
degree.

As consequence, the dedicated test will demand more test cases to be sure to
cover all hydration scenarios.

## Pokemon replication, user management and security

In order to provide the next features, one pre-requisite emerges: user
management. And user management also speaks with security (Authentication and
authorization).

So, i decided to handle security before move forward with the pokemon
replication functionality. For spring security, i wrote a
[small article][security] a while ago which will be very handy now.

[security]: https://sombriks.com.br/blog/0085-spring-security-configuration/

### A minimal authentication infrastructure.

For this exercise i decided to go with Sessionless authorization. i'll check for
the JWT and get user info from it.

I decided that a fancier third party auth integration would be too much, and
also i have the time constraint here.

### Key pair generation

Important not, for this example i am generating the RSA key pair every
application startup. Again, it's done this way doe time constraints, but one
important aspect must be on radar regarding the service horizontal scaling.

A true production-ready would delegate the keys management to a centralized
point of authority, otherwise each service instance will have its own key, with
in turn beaks the entire purpose of scaling.

## Database modelling

Now that i have an user, that user can have customized pokemons. The naive
approach could be simply model tables in the shape of the current service
payload as seen in the `PokemonDto` and other related domains, map it to JPA
entities and call it a day.

But then, there is the custom attributes functionality.

How custom is it supposed to be?

At first, a custom JSON column in the tables could solve it pretty quick and
hacky, or even adopt a NoSQL database altogether, but the main point on any
database supposed to gather any valuable information is also to guarantee the
quality of this data.

So, let's not only stick with the relational paradigm, but also with a decent
modeling able to extract as much information as possible from the users.

I'll stuck the JSOM column in the model, but the *flexible design* i will
interpret as proper database schema evolution through liquibase migrations.

In practice, over the system lifetime, some custom data could be identified as
potential firs-class citizen and then the proper sql scripts would be created to
promote the alter tabler operations and then the [merge/update][sql-merge]
operations.

[sql-merge]: https://www.postgresql.org/docs/current/sql-merge.html

Long story short: my flexible design is a custom json column and posterior
liquibase scripts when first-class data emerges from custom user data.

### Time-series data

I will also model the user data as cube, so all changes over the time will be
preserved and cool data analysis can be done on top of it.

The strategy will be the longitudinal schema + snapshots. I wrote briefly about
it [here][longitudinal-schema].

[longitudinal-schema]: https://sombriks.com.br/blog/0086-database-design-patterns/


