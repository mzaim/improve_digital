Assumptions:

1. Words are separated by spaces, punctuation does not exist
2. Input files are in classpath
3. If a word does not appear in one file then we'll display its occurrences as 0 for that file
4. I wrote automated tests for the "worker" logic, not the thread logic.
5. I manually tested the thread logic. (Perhaps a framework could be used to do that but I felt it was outside of scope)

Implementation:
Singleton cache class to be used all over the project - Cache
Main class that does thread management - Main
Worker class for file reading - CustomFileReader
Worker class for cache display - CachePrinter