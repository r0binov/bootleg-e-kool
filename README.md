# bootleg-e-kool

## In order to get data into the database after you have run the backend application and the schema has been created.


INSERT INTO student (name, email)
VALUES
    ('John Doe', 'johndoe@example.com'),
    ('Alice Smith', 'alicesmith@example.com'),
    ('Bob Johnson', 'bob.j@example.com'),
    ('Eve Brown', 'eve.brown@example.com'),
    ('Charlie Wilson', 'charlie@example.com');


INSERT INTO subject (subject_name)
VALUES
    ('Mathematics'),
    ('History'),
    ('Science'),
    ('English'),
    ('Computer Science');


INSERT INTO grade (student_id, subject_id, grade)
VALUES
    (1, 1, 90),
    (1, 2, 85),
    (2, 1, 88),
    (2, 2, 92),
    (3,1,1),
    (3,2,10),
    (4,1,69),
    (4,2,13),
    (5,1,37),
    (5,2,69)

## Running the frontend application

First, install angular-cli globally:
```
npm install -g @angular/cli
``` 

Then navigate to frontend project root and install required dependencies:
```
npm install
```

When dependencies are installed run the frontend application:
```
ng serve
```

and navigate to `http://localhost:4200` in your browser


# Have fun!
