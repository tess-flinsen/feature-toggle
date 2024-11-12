# Cosmo Cats Feature Toggle System

This project is designed to learn and implement Aspect-Oriented Programming (AOP) and feature toggles using Java and the Spring Framework. The system enables or disables specific features for "cosmo-cats" based on environment configurations. By toggling flags, you can dynamically control which features are available, allowing you to adapt the functionality without changing the code or redeploying the application.

## Key Features

1. **Feature Toggle Service**:
   - Manages and checks feature flags stored in environment configuration.
   - Provides methods to verify if a feature is enabled or disabled before executing related functionality.

2. **Aspect-Oriented Programming (AOP)**:
   - `FeatureToggleAspect` intercepts method calls and checks whether the associated feature flag is active.
   - If the flag is enabled, the method proceeds. If the flag is disabled, an exception (`FeatureNotAvailableException`) is thrown.

3. **Example Feature Toggle**:
   - `CosmoCatService` uses the `feature.cosmoCats.enabled` flag to control the availability of the `getCosmoCats()` method.
   - If the flag is turned off, the method throws an exception, preventing access to the cosmo-cats feature.

## Testing

- Unit tests verify that the system behaves as expected:
  - **Feature Enabled**: Ensures methods execute when features are enabled.
  - **Feature Disabled**: Ensures exceptions are thrown when features are disabled.

This system provides a flexible way to manage feature availability, improving maintainability and adaptability in production environments.
