public class EngineFactory {
    public static Engine getEngine(String type){
        if(type.equalsIgnoreCase("disel")){
            return new DiselEngine();
        }
        else if(type.equalsIgnoreCase("petrol")){
            return new PetrolEngine();
        }
        else{
            throw new IllegalArgumentException("Invalid Engine Type");
        }
    }
}
