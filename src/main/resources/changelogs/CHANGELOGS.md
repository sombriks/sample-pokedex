# Database Changelogs

The application is always subject to the current database state. Applications
come and go, but the data remains.

On the other hand, the database state must be trusted and known beforehand by
the application, and the best way to get it is by control.

This is why i always setup some database migration solution, like
[liquibase][liquibase] or [knexjs migrations][knex] on applications under my
watch.

[liquibase]: https://liquibase.com/
[knex]: https://knexjs.org/
