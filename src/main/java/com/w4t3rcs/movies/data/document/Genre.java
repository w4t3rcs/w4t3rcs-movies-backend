package com.w4t3rcs.movies.data.document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor @NoArgsConstructor
@Document(collection = "genres")
public class Genre {
    @Id
    private ObjectId id;
    private String name;
}
