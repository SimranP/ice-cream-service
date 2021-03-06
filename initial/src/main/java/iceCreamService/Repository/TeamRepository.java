package iceCreamService.Repository;

import iceCreamService.Domain.Team;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TeamRepository extends MongoRepository<Team,String> {
    void deleteByEmail(String email);
}
