# BooksSystem
Create a Database and Collections
db.createCollection("books")
db.createCollection("categories")

2. Insert Documents into the books Collection
db.books.insertMany([
    {
        title: "Introduction to MongoDB",
        author: "John Doe",
        publishedDate: new Date("2021-01-15"),
        categories: ["Database", "NoSQL"],
        availableCopies: 5
    },
    {
        title: "Spring Boot with MongoDB",
        author: "Jane Smith",
        publishedDate: new Date("2022-06-10"),
        categories: ["Programming", "Spring"],
        availableCopies: 8
    },
    {
        title: "Advanced MongoDB Techniques",
        author: "Alice Johnson",
        publishedDate: new Date("2020-03-25"),
        categories: ["Database", "Advanced"],
        availableCopies: 3
    }
])

3. Insert Documents into the categories Collection
db.categories.insertMany([
    { name: "Database" },
    { name: "Programming" },
    { name: "Spring" }
])

Querying Data
1. Query on Embedded/Nested Documents
db.books.find({ categories: "Database" })

Indexing Documents
1. Create Indexes on Fields
db.books.createIndex({ title: 1 })
db.books.createIndex({ author: 1 })

2. Use Indexes to Optimize Queries
db.books.find({ title: "Introduction to MongoDB" })

Aggregation Framework
1. Count Books by Each Category
db.books.aggregate([
    { $unwind: "$categories" },
    { $group: { _id: "$categories", count: { $sum: 1 } } },
    { $project: { category: "$_id", count: 1, _id: 0 } }
])
2. Get Books Published After a Certain Date db.books.aggregate([
    { $match: { publishedDate: { $gt: new Date("2022-01-01") } } }
])

3. Get Authors with More Than a Certain Number of Books 

db.books.aggregate([
    { $group: { _id: "$author", totalBooks: { $sum: 1 } } },
    { $match: { totalBooks: { $gt: 1 } } },
    { $project: { author: "$_id", totalBooks: 1, _id: 0 } }
])


