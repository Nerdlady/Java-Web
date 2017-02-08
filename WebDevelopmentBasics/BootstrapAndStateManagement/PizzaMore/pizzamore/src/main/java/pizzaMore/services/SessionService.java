package pizzaMore.services;

import pizzaMore.models.entities.Session;
import pizzaMore.repositories.SessionRepository;

public class SessionService {
    private SessionRepository sessionRepository;

    public SessionService() {
        this.sessionRepository = new SessionRepository();
    }

    public long create(Session session){
        return this.sessionRepository.createSession(session);
    }

    public Session findById(Long id){
        return this.sessionRepository.findById(id);
    }

    public void delete(Long id){
        this.sessionRepository.delete(id);
    }
}
