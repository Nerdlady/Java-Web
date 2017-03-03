package pizzaForum.repositories;

import pizzaForum.entities.Reply;

import java.util.List;

public interface ReplyRepository {
    void save(Reply reply);
    List<Reply> getAll(Long id);
}
