package com.nticoding.mchat.presentation.chat

import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import com.nticoding.mchat.MainActivity
import com.nticoding.mchat.domain.model.Conversation
import com.nticoding.mchat.domain.repository.ConversationRepository
import com.nticoding.mchat.domain.use_case.GetConversationMessagesUseCase
import com.nticoding.mchat.domain.use_case.InsertMessageUseCase
import com.nticoding.mchat.ui.theme.MChatTheme
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import com.nticoding.mchat.presentation.ConversationRepositoryFake

@HiltAndroidTest
class ChatScreenTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    private lateinit var getConversationMessagesUseCase: GetConversationMessagesUseCase
    private lateinit var insertMessageUseCase: InsertMessageUseCase
    private lateinit var conversationRepository: ConversationRepository
    private lateinit var chatViewModel: ChatViewModel

    @Before
    fun setUp() {

        conversationRepository = ConversationRepositoryFake()

        getConversationMessagesUseCase = GetConversationMessagesUseCase(conversationRepository)
        insertMessageUseCase = InsertMessageUseCase(conversationRepository)

        chatViewModel = ChatViewModel(
            0,
            getConversationMessagesUseCase,
            insertMessageUseCase
        )

        composeRule.activity.setContent {
            MChatTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    // NOTE: In a real app these would likely be passed as navigation arguments
                    // hardcoded here for simplicity
                    ChatScreen(
                        conversation = Conversation(
                            0,
                            "Sarah"
                        ),
                        viewModel = chatViewModel
                    )
                }
            }
        }
    }

    @Test
    fun conversationSectionsExist() {

        while (chatViewModel.state.messages.isEmpty())

        composeRule
            .onNodeWithText("Wednesday")
            .assertExists()

        composeRule
            .onNodeWithText("10:02")
            .assertExists()

        composeRule
            .onNodeWithText("09:00")
            .assertExists()
    }

    @Test
    fun sendMessageButtonEnabledMessageExists() {

        composeRule.onNodeWithTag("UserInputTextEntryBox")
            .assertExists()
            .performTextInput("Test message")

        composeRule.onNodeWithContentDescription("Send.")
            .performClick()

        composeRule.onNodeWithText("Test message")
            .assertExists()
    }
}