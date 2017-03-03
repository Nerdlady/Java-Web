package pizzaForum.services;

import pizzaForum.models.bindingModels.reply.NewReply;
import pizzaForum.models.viewModels.ViewReply;

import java.util.List;

public interface ReplyService {
    void save(NewReply reply);
    List<ViewReply> getAllReplies(Long id);
}
