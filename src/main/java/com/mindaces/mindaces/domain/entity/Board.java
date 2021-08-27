package com.mindaces.mindaces.domain.entity;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@DynamicInsert
@Table(name = "BOARD")
public class Board extends BaseTimeEntity
{

    //gallery 이름, user 이름으로 foregin key 설정할거임 이거 칼럼에서 추가하는거 있잖아 그거 하자
    @Column(nullable = false,length = 45)
    private String gallery;

    @Column(nullable = false)
    private String user;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "content_idx")
    private Long contentIdx;

    @Column(nullable = false,length = 45)
    private String title;

    @Column(columnDefinition="text",nullable = false)
    private String content;

    @Column(columnDefinition = "bigint default 0")
    private Long likes;

    @Column(name="dis_likes",columnDefinition = "bigint default 0")
    private Long disLikes;

    @Column(length = 80,nullable = false)
    private String password;

    @Column(columnDefinition = "tinyint(1) default 0",name = "is_logged_user")
    private Long isLoggedUser;

    @Builder
    public Board(String gallery,String user,Long contentIdx,String title,String content,Long likes,Long disLikes,String password,Long isLoggedUser)
    {
        this.gallery = gallery;
        this.user = user;
        this.contentIdx = contentIdx;
        this.title = title;
        this.content = content;
        this.likes = likes;
        this.disLikes = disLikes;
        this.password = password;
        this.isLoggedUser = isLoggedUser;
    }

}
