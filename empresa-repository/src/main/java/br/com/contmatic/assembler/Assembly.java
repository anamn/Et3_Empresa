package br.com.contmatic.assembler;

import org.bson.Document;

public interface Assembly<T,D extends Document> {

    T toResource(D document);
    
    D toDocument(T resource);
}
