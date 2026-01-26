# [sample-pokedex](https://github.com/sombriks/sample-pokedex)

[![Run Maven Tests](https://github.com/sombriks/sample-pokedex/actions/workflows/test.yml/badge.svg)](https://github.com/sombriks/sample-pokedex/actions/workflows/test.yml)

Sample spring boot project with IsC artifacts

## Features

## Requirements

This project requires the following platforms to run:

- [java 25](https://dev.java)
- [docker 29](https://docs.docker.com)

All development was done using [Fedora 43 Workstation KDE edition][fedora] and
[Intellij *ultimate*][intellij] as main IDE.

[fedora]: https://fedoraproject.org/kde/
[intellij]: https://www.jetbrains.com/idea/

## How to run

The easiest way is to run with docker compose:

```bash
docker compose -f src/infra/compose/docker-compose-dev.yml up
```

The service will listen on <http://localhost:8080>

Other integrations (CI/CD pull based GitOps with ArgoCD) are planned.

## Development

The best development ergonomics is to run a local environment and run the
auxiliary compose scripts to offer the services needed for proper work:

```bash
docker compose -f src/infra/compose/docker-compose-db.yml up
```

The image docker can be build with this command:

```bash
# on project/repository root
docker build -f src/infra/Dockerfile -t sombriks/restapi:dev .
```

## Noteworthy

- [spring boot help](docs/HELP.md)
- [devlog](docs/DECISIONS.md)
- [jvm tuning](docs/JVM_CONFIG.md)
- [sample pokeapi requests](docs/pokeapi-requests/pokeapi.http)
