package com.example.roommateproject.FrontPage.Components

/*

/*
                                          **********************
                                             * News Tab MIS *
                                          **********************
 */


@Composable
fun NewsTab() {
    val accountService: AccountService = AccountService();

    val sortedEvents = AccountService.currentEvents.sortedByDescending { it.timeStamp }

    Box(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(30.dp)),
        contentAlignment = Alignment.TopCenter,
    ) {
        Box(
            modifier = Modifier
                .background(boxLayerGrey)
                .fillMaxHeight(0.25f)
                .fillMaxWidth(0.85f)
                .clip(shape = RoundedCornerShape(52.dp)),
            contentAlignment = Alignment.Center,
        ) {
            Box(
                modifier = Modifier
                    .background(Color.White)
                    .fillMaxHeight(0.9f)
                    .verticalScroll(ScrollState(1), true)
                    .fillMaxWidth(0.92f),
                contentAlignment = Alignment.TopCenter
            ) {
                Column(modifier = Modifier,
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Row(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(bottom = 4.dp),
                    ) {
                        Text(
                            text = "News",
                            color = earthyBrown,
                            fontFamily = jaldiFontFamily,
                            fontSize = 25.sp
                        )
                    }
                    val eventString = sortedEvents.joinToString("\n\n") { "${it.timeStamp} \n  ${it.eventType.eventText}" }
                    Text(
                        text = eventString,
                        fontFamily = jaldiFontFamily,
                        color = Color.DarkGray,
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}

 */