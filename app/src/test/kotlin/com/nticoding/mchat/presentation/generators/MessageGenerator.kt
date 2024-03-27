package com.nticoding.mchat.presentation.generators

import com.nticoding.mchat.domain.model.Message

internal val messages = listOf(
    Message(authorId = 0, content = "Hi", timestamp = 1711526400000), // 27.3.2024 08:00:00 GMT
    Message(
        authorId = 0,
        content = "How are you?",
        timestamp = 1711526410000
    ), // 27.3.2024 08:00:10 GMT
    Message(
        authorId = 0,
        content = "Wyd?",
        timestamp = 1711526415000
    ), // 27.3.2024 08:00:15 GMT
    Message(authorId = 1, content = "Hey", timestamp = 1711526460000), // 27.3.2024 08:01:00 GMT
    Message(
        authorId = 1,
        content = "Not much",
        timestamp = 1711526481000
    ), // 27.3.2024 08:01:21 GMT
    Message(authorId = 1, content = "U?", timestamp = 1711526520000), // 27.3.2024 08:02:00 GMT
    Message(
        authorId = 0,
        content = "Same",
        timestamp = 1711530121000
    ), // 27.3.2024 09:02:01 GMT
    Message(
        authorId = 0,
        content = "Chilling, Killing",
        timestamp = 1711533721000
    ), // 27.3.2024 10:02:01 GMT
    Message(
        authorId = 1,
        content = "Cool",
        timestamp = 1711533739000
    ), // 27.3.2024 10:02:19 GMT
)