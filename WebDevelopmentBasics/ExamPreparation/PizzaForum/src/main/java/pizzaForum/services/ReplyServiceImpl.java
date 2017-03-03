package pizzaForum.services;

import pizzaForum.entities.Reply;
import pizzaForum.models.bindingModels.reply.NewReply;
import pizzaForum.models.viewModels.ViewReply;
import pizzaForum.repositories.ReplyRepository;
import pizzaForum.utils.ModelParser;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class ReplyServiceImpl implements ReplyService {
    @Inject
    private ReplyRepository replyRepository;

    @Override
    public void save(NewReply reply) {
        Reply replyToPersist = ModelParser.getInstance().map(reply,Reply.class);
        this.replyRepository.save(replyToPersist);
    }

    @Override
    public List<ViewReply> getAllReplies(Long id) {
        List<Reply> replies = this.replyRepository.getAll(id);
        List<ViewReply> viewReplies = new ArrayList<>();
        for (Reply reply : replies) {
            ViewReply viewReply = ModelParser.getInstance().map(reply,ViewReply.class);
            viewReplies.add(viewReply);
        }
        return viewReplies;
    }
}
