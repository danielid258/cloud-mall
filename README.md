Ribbon
    LoadBalance

Feign
    communicate between services,and use Ribbon for LoadBalance
    1 add dependency
         <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-feign </artifactId>
        </dependency>
    2 add @EnableFeignClients to Application class

    3 create new interface, add @FeignClient to the new interface
        @FeignClient(name = "product-service")
        public interface ProductApi
    4 call the remote service by @PostMapping in this interface
        @GetMapping("/remote/serve")
        String productRemoteServe();

    4 autowired the interface where you need to use it
        @Autowired
        private ProductApi productApi;

        public String remoteCallByFeign() {
            String object = productApi.productRemoteServe();
            return object;
        }
