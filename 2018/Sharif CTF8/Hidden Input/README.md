# Hidden Input, Web, 50pts

## Problem

Can you log in?

## Solution

Getting a hint from the name of the question, it has somethingto do with hidden input. On viewing the source code, we find that there is a hidden input, **debug**. On turning it to **1** and entering random values in the form and submitting, we see from the output that this is a case of **SQL Injection**. On analysing the SQL query, we come up with `') OR ('a' = 'a` as an input which gets through the SQL query.

So, we get the flag `SharifCTF{}`
