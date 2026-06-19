public class Computer {
    private String cpu;
    private String ram;
    private String storage;
    private String gpu;
    private String motherboard;
    
    private Computer(Builder builder) {
        this.cpu = builder.cpu;
        this.ram = builder.ram;
        this.storage = builder.storage;
        this.gpu = builder.gpu;
        this.motherboard = builder.motherboard;
    }
    
    public static class Builder {
        private String cpu;
        private String ram;
        private String storage;
        private String gpu;
        private String motherboard;
        
        public Builder setCpu(String cpu) {
            this.cpu = cpu;
            return this;
        }
        
        public Builder setRam(String ram) {
            this.ram = ram;
            return this;
        }
        
        public Builder setStorage(String storage) {
            this.storage = storage;
            return this;
        }
        
        public Builder setGpu(String gpu) {
            this.gpu = gpu;
            return this;
        }
        
        public Builder setMotherboard(String motherboard) {
            this.motherboard = motherboard;
            return this;
        }
        
        public Computer build() {
            return new Computer(this);
        }
    }
    
    @Override
    public String toString() {
        return "Computer{" +
                "cpu='" + cpu + '\'' +
                ", ram='" + ram + '\'' +
                ", storage='" + storage + '\'' +
                ", gpu='" + gpu + '\'' +
                ", motherboard='" + motherboard + '\'' +
                '}';
    }
}
