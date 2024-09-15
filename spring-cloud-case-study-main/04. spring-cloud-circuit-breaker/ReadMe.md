## spring-cloud-circuit-breakers

In Spring Cloud, a circuit breaker is a design pattern used to improve the resilience of distributed systems by detecting and gracefully handling failures and latency issues in remote service calls. It helps prevent cascading failures and provides fault tolerance in microservices architectures.

Here's a breakdown of how circuit breakers work in Spring Cloud:

- **Detecting Failures:** When a service call is made, the circuit breaker monitors the response. If the service consistently responds with errors or takes too long to respond, the circuit breaker marks the service as "unhealthy."

- **Tripping the Circuit:** Once a certain threshold of failures or timeouts is reached, the circuit breaker "trips," effectively stopping further requests from being sent to the failing service. Instead, it routes requests to a fallback mechanism, which can be a default response or a cached response.

- **Half-Open State:** After a period of time, the circuit breaker enters a "half-open" state, during which it allows a limited number of requests to pass through to the service. If these requests succeed, the circuit breaker resets, and normal operation resumes. If any of the requests fail, the circuit breaker returns to the "open" state.


Spring Cloud provides integration with Netflix Hystrix, a library that implements the circuit breaker pattern. Hystrix offers features such as circuit breaking, fallbacks, request caching, and metrics gathering.

![](https://gitlab.nenosystems.in/cuickdevteam/spring-cloud-case-study/-/wikis/uploads/e67bf804b653695722b2a1951e802145/circuit_breaker_v1.png)

- **registerHealthIndicator:** This parameter, when set to true, registers a health indicator for the circuit breaker. A health indicator provides insights into the current state of the circuit breaker, allowing external systems to monitor its status.
 
- **eventConsumerBufferSize:** This sets the size of the buffer for events consumed by the circuit breaker. Events can include state transitions, failures, successes, etc. Setting a buffer size helps in managing the flow of events and preventing overflow.
 
- **failureRateThreshold:** This parameter defines the threshold for the failure rate. If the failure rate (percentage of failed calls) exceeds this threshold, the circuit breaker will open, preventing further calls from reaching the failing service.
 
- **minimumNumberOfCalls:** This sets the minimum number of calls that need to be made before the circuit breaker starts calculating the failure rate and considering state transitions. It helps prevent premature decisions based on a small number of calls.
 
- **automaticTransitionFromOpenToHalfOpenEnabled:** When enabled, this feature automatically transitions the circuit breaker from an open state to a half-open state after a certain duration (waitDurationInOpenState). This allows the system to periodically check if the failing service has recovered without manual intervention.
 
- **waitDurationInOpenState:** This specifies the duration the circuit breaker remains in an open state before transitioning to a half-open state. In this case, it's set to 6 seconds. During this time, the circuit breaker blocks calls to the failing service.
 
- **permittedNumberOfCallsInHalfOpenState:** This determines the number of calls permitted in the half-open state. When the circuit breaker transitions to a half-open state, only a limited number of calls are allowed to determine if the service has recovered.
 
- **slidingWindowSize:** This parameter sets the size of the sliding window used for monitoring. The sliding window tracks the outcome of calls (successes, failures) over a specified period.
 
- **slidingWindowType:** This parameter defines the type of sliding window used for monitoring. In this case, it's set to COUNT_BASED, indicating that the sliding window is based on the count of calls.
