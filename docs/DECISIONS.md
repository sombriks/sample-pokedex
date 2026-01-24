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

[hatgeoas]: https://htmx.org/essays/hateoas/

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
