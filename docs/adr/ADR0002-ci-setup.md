# ADR0002 – Continuous Integration with GitHub Actions

## Status
Accepted

## Context
The project requires an automated way to verify that the Ant build
succeeds on clean environments for each change.

## Decision
GitHub Actions is used to run the Ant build on every push and pull request
to the main branch.

## Consequences
- Build failures are detected early
- Contributors receive immediate feedback
- CI configuration is versioned with the codebase
