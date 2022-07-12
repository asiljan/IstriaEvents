# IstriaEvents application

Idea of the app is to help users find interesting events, attractions and even restaurants
nearby or within given city, municipality etc.

## Architecture

Application is written by following MVI architecture pattern with additional UseCase layer
Idea is to have useCase implementation per some action. For example, user authentication - `AuthenticateUseCase`
