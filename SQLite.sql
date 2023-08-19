USE socialMedia;

CREATE TABLE users (
    user_id INTEGER PRIMARY KEY,
    user_name TEXT
);

CREATE TABLE posts (
    post_id INTEGER PRIMARY KEY,
    user_id INTEGER NOT NULL,
    post_text TEXT,
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE Likes (
    like_id INTEGER PRIMARY KEY,
    post_id INTEGER,
    user_id INTEGER,
    FOREIGN KEY (post_id) REFERENCES posts(post_id),
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE Comment (
    comment_id INTEGER PRIMARY KEY,
    comment_text TEXT,
    post_id INTEGER,
    user_id INTEGER,
    FOREIGN KEY (post_id) REFERENCES posts(post_id),
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);

INSERT  INTO  users VALUES 
							(1, "sina"), 
							(2, "nisha"), 
							(3, "rina");

INSERT  INTO  posts VALUES 
							(1, 1 , "hello, World!"), 
							(2, 2 , "same thing!"), 
							(3, 3 , " not the same thing!");

INSERT INTO Likes (like_id , post_id, user_id) VALUES 
												(1, 1, 1),
												(2, 2, 2), 
												(3, 3, 3) ;

INSERT INTO Comment (comment_id,comment_text, post_id, user_id) VALUES 
														(1,'cool!', 1, 1), 
														(2,'very cool!',2,2), 
														(3,'nice!', 3, 3) ;

SELECT * FROM Comment;
SELECT * FROM posts;
