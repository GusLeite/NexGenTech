package br.com.gusleite.NexGenTech.mapper;

import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class NexGenMapper {

    private static final ModelMapper mapper = new ModelMapper();

    public static <O, D> D parseObject(O origem, Class<D> destination){
        return mapper.map(origem,destination);
    }
    public static <O, D> List<D> parseListObjects(List<O> origin, Class<D> destination){
        List<D> destinationObjects = new ArrayList<D>();
        for (O o: origin) {
            destinationObjects.add(mapper.map(origin,destination));
        }
        return destinationObjects;
    }
}
