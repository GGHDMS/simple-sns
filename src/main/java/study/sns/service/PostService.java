package study.sns.service;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.sns.exception.ErrorCode;
import study.sns.exception.SnsApplicationException;
import study.sns.model.Comment;
import study.sns.model.Post;
import study.sns.model.entity.CommentEntity;
import study.sns.model.entity.LikeEntity;
import study.sns.model.entity.PostEntity;
import study.sns.model.entity.UserEntity;
import study.sns.repository.CommentEntityRepository;
import study.sns.repository.LikeEntityRepository;
import study.sns.repository.PostEntityRepository;
import study.sns.repository.UserEntityRepository;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostEntityRepository postEntityRepository;
    private final UserEntityRepository userEntityRepository;
    private final LikeEntityRepository likeEntityRepository;
    private final CommentEntityRepository commentEntityRepository;

    @Transactional
    public void create(String title, String body, String userName) {
        UserEntity userEntity = getUserOrException(userName);
        //post save
        postEntityRepository.save(PostEntity.of(title, body, userEntity));
    }

    @Transactional
    public Post modify(String title, String body, String userName, Long postId) {
        UserEntity userEntity = getUserOrException(userName);
        PostEntity postEntity = getPostOrException(postId);

        //post permission
        if (postEntity.getUser() != userEntity) {
            throw new SnsApplicationException(ErrorCode.INVALID_PERMISSION, String.format("%s has no permission with %s", userName, postId));
        }

        postEntity.setTitle(title);
        postEntity.setBody(body);

        return Post.fromEntity(postEntityRepository.saveAndFlush(postEntity));
    }

    @Transactional
    public void delete(String userName, Long postId) {
        UserEntity userEntity = getUserOrException(userName);
        PostEntity postEntity = getPostOrException(postId);

        //post permission
        if (postEntity.getUser() != userEntity) {
            throw new SnsApplicationException(ErrorCode.INVALID_PERMISSION, String.format("%s has no permission with %s", userName, postId));
        }

        postEntityRepository.delete(postEntity);
    }

    public Page<Post> list(Pageable pageable) {
        return postEntityRepository.findAll(pageable).map(Post::fromEntity);
    }

    public Page<Post> my(String userName, Pageable pageable) {
        UserEntity userEntity = getUserOrException(userName);

       return postEntityRepository.findAllByUser(userEntity, pageable).map(Post::fromEntity);
    }

    @Transactional
    public void like(Long postId, String userName) {
        UserEntity userEntity = getUserOrException(userName);
        PostEntity postEntity = getPostOrException(postId);

        // check liked -> throw
        likeEntityRepository.findByUserAndPost(userEntity, postEntity)
                .ifPresent(it -> {
                    throw new SnsApplicationException(ErrorCode.ALREADY_LIKED, String.format("userName %s already like post %s", userName, postId));
                });

        //like save
        likeEntityRepository.save(LikeEntity.of(userEntity, postEntity));
    }

    public Integer likeCount(Long postId) {
        PostEntity postEntity = getPostOrException(postId);

        // check liked -> throw
        return likeEntityRepository.countAllByPost(postEntity);
    }

    @Transactional
    public void comment(Long posId, String userName, String comment) {
        UserEntity userEntity = getUserOrException(userName);
        PostEntity postEntity = getPostOrException(posId);

        //comment save
        commentEntityRepository.save(CommentEntity.of(userEntity, postEntity, comment));
    }

    public Page<Comment> getComments(Long postId, Pageable pageable) {
        PostEntity postEntity = getPostOrException(postId);
        return commentEntityRepository.findAllByPost(postEntity, pageable).map(Comment::fromEntity);
    }
    //post exist
    private PostEntity getPostOrException(Long postId) {
        return postEntityRepository.findById(postId).orElseThrow(() ->
                new SnsApplicationException(ErrorCode.POST_NOT_FOUND, String.format("%s not founded", postId)));
    }

    // user exist
    private UserEntity getUserOrException(String userName) {
        return userEntityRepository.findByUserName(userName)
                .orElseThrow(() -> new SnsApplicationException(ErrorCode.USER_NOT_FOUND, String.format("%s not founded", userName)));
    }

}
