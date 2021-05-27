package com.example.composelayouts

import android.os.Bundle
import android.text.Layout
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.composelayouts.ui.theme.ComposeLayoutsTheme
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeLayoutsTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    ShopHomeScreen()
                }


            }
        }
    }
}


@Composable
@Preview(showBackground = true, showSystemUi = false)
fun ShopHomeScreen() {

    val shoeList  = listOf(
        Shoe("499",R.color.lightPink,R.drawable.shoe1),
        Shoe("899",R.color.lightBlue,R.drawable.shoe2),
        Shoe("999",R.color.lightIndigo,R.drawable.shoe3)
    )

    //now create shoecard according to shoeList;

    Column(
        Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.backgroundBlue))
            .padding(horizontal = 20.dp, vertical = 8.dp)
    ) {


        IconButton(onClick = { /*TODO*/ }) {
            Icon(Icons.Default.ArrowBack, contentDescription = "", tint = Color.Black)
        }
        buildSpacer()
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Proche Design",
            style = TextStyle(color = Color.Black, fontSize = 30.sp, fontWeight = FontWeight.Bold),
            textAlign = TextAlign.Center
        )

        buildSpacer()

        Text(
            "list of all imported design shoes",
            style = TextStyle(fontSize = 14.sp, color = Color.Black)
        )

        buildSpacer()
//
        for(c in shoeList)
        {
            buildSingleShoeCard(shoe = c)
        }


//        buildSingleShoeCard()



        //we are done with the design if you like this video plz subscribe to the channel and hit the bell icon

    }
}

//build single shoe card
// for single card layout we use constraint layout
@Composable
fun buildSingleShoeCard(shoe:Shoe) {

    ConstraintLayout(
        Modifier
            .fillMaxWidth()
            .height(230.dp)
    ) {

        val (addButton, bgImage, shoeImage, priceTag) = createRefs()

        Surface(
            Modifier
                .fillMaxWidth(.5f)
                .fillMaxHeight()
                .clip(
                    shape = RoundedCornerShape(50.dp)
                )
                .constrainAs(bgImage) {
                    start.linkTo(parent.start, margin = 8.dp)
                }, color = colorResource(id = shoe.color)
        ) {

        }
        Box(
            Modifier
                .height(46.dp)
                .width(46.dp)
                .clip(shape = RoundedCornerShape(20.dp))
                .background(color = Color.White)
                .constrainAs(
                    addButton
                ) {
                    top.linkTo(parent.top)
                }, contentAlignment = Alignment.Center
        ) {

            Icon(Icons.Default.Add, contentDescription = "")
        }


        //we need to rotate the image

        Image(
            modifier = Modifier
                .fillMaxWidth(.6f)
                .constrainAs(shoeImage) {
                    centerVerticallyTo(parent)

                    start.linkTo(parent.start, margin = 30.dp)

                }
                .rotate(-25f), painter = painterResource(id = shoe.image), contentDescription = ""
        )


        //done with rotation now need to shift shoe image sligh to right side


        Text(modifier = Modifier.constrainAs(priceTag){
                                                      end.linkTo(parent.end,margin = 30.dp)
            bottom.linkTo(parent.bottom,margin =20.dp)
        },text = shoe.price,style = TextStyle(color = Color.Black,fontSize = 24.sp,fontWeight = FontWeight.Bold))

    }

}

//done with single showCard

data class Shoe(val price:String,val color :Int,val image :Int)


@Composable
fun buildSpacer() {
    Spacer(Modifier.height(10.dp))
}


//Row for arrange elements in horizontal order
//Column for arrange elements in Vertical order
//Boxes
//Constratint Layout


@ExperimentalMaterialApi

@Composable
fun plantHomeScreen() {


    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(color = Color.White)
    ) {

        val (topBar, logoBar, searchBar, topSlide, bottomSlide, bottomBar) = createRefs()


        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(.25f)
                .clip(
                    shape = RoundedCornerShape(
                        bottomStart = 50.dp,
                        bottomEnd = 50.dp
                    )
                )
                .constrainAs(topBar) {
                    top.linkTo(parent.top)
                }, color = colorResource(id = R.color.greenColor)
        ) {

        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(logoBar) {
                    centerTo(topBar)
                }
                .padding(horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("PlantShoppy", style = TextStyle(color = Color.White, fontSize = 30.sp))

            Image(
                modifier = Modifier
                    .width(60.dp)
                    .height(60.dp),
                painter = painterResource(id = R.drawable.logo),
                contentDescription = ""
            )
        }


        Box(modifier = Modifier
            .fillMaxWidth(.8f)
            .height(48.dp)
            .constrainAs(searchBar) {
                top.linkTo(topBar.bottom, margin = -24.dp)
                centerHorizontallyTo(parent)
            }
            .clip(shape = RoundedCornerShape(20.dp))
            .background(color = Color.White)
            .padding(horizontal = 20.dp), contentAlignment = Alignment.Center) {

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Search ")

                Icon(Icons.Outlined.Search, contentDescription = "", tint = Color.Black)
            }

        }

        Column(modifier = Modifier
            .padding(20.dp)
            .constrainAs(topSlide) {

                top.linkTo(searchBar.bottom, margin = 10.dp)

            }) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Recommended Plants ", style = TextStyle(fontSize = 18.sp))

                Text(
                    "more",
                    style = TextStyle(
                        fontSize = 16.sp,
                        color = colorResource(id = R.color.greenColor)
                    )
                )

            }

            LazyRow(content = {

                item {
                    itemCard(width = 180.dp, image = R.drawable.image_1, "Samarthi")
                    itemCard(width = 180.dp, image = R.drawable.image_2, "Neem")
                    itemCard(width = 180.dp, image = R.drawable.image_3, "Peeppali")
                }

            })

        }
        Column(modifier = Modifier
            .padding(20.dp)
            .constrainAs(bottomSlide) {

                top.linkTo(topSlide.bottom, margin = 10.dp)

            }) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Featured Plants ", style = TextStyle(fontSize = 18.sp))

                Text(
                    "more",
                    style = TextStyle(
                        fontSize = 16.sp,
                        color = colorResource(id = R.color.greenColor)
                    )
                )

            }

            LazyRow(content = {

                item {
                    itemCard(width = 260.dp, image = R.drawable.image_1, "Samarthi")
                    itemCard(width = 260.dp, image = R.drawable.image_2, "Neem")

                }

            })

        }

        BottomNavigation(
            modifier = Modifier
                .constrainAs(bottomBar) {
                    bottom.linkTo(parent.bottom)
                }
                .clip(
                    shape = RoundedCornerShape(
                        topStart = 10.dp,
                        topEnd = 10.dp
                    )
                ),

            backgroundColor = colorResource(id = R.color.greenColor)


        ) {

            BottomNavigationItem(selected = true, onClick = { /*TODO*/ },
            icon = {Icon(Icons.Filled.Home,contentDescription ="",tint = Color.White)})

            BottomNavigationItem(selected = false, onClick = { /*TODO*/ },
            icon = {Icon(Icons.Outlined.Favorite,contentDescription ="",tint =  Color.Black)})

        }

    }
}










@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Composable
fun RowExample() {
    Row(horizontalArrangement = Arrangement.Center) {


        for (i in 1..3)
            RowElement()
    }
}

@Composable
fun RowElement() {
    Row(
        modifier = Modifier
            .padding(10.dp)
            .background(color = Color.Blue)
    ) {
        Icon(Icons.Default.Person, contentDescription = "jhjhjhh")

        Text("Hello")
    }
}

@Composable
fun ColumnElement() {
    Row(
        modifier = Modifier
            .padding(10.dp)
            .background(color = Color.Yellow)
    ) {
        Icon(Icons.Default.Settings, contentDescription = "jhjhjhh")

        Text("Hello")
    }
}

@Composable
fun ColumnExample() {
    Column(verticalArrangement = Arrangement.SpaceAround) {

        ColumnElement()
        ColumnElement()
        ColumnElement()


    }
}

@ExperimentalMaterialApi
@Composable
fun ConstraintExample() {

    ConstraintLayout(modifier = Modifier.fillMaxSize()) {

        val (card,topImage,addButton,text,thumIcon)= createRefs()


        Card(modifier = Modifier
            .constrainAs(card) {
                centerTo(parent)
            }
            .fillMaxWidth(.9f)
            .fillMaxHeight(.4f)

        ){



        }

        Surface(modifier = Modifier
            .constrainAs(topImage) {

                top.linkTo(card.top)

                start.linkTo(card.start)

            }
            .fillMaxWidth(.9f)
            .fillMaxHeight(.2f),
            color = Color.Yellow
        ) {}


        FloatingActionButton(

            modifier = Modifier.constrainAs(addButton){
                          top.linkTo(topImage.bottom,margin = (-20).dp)
                          end.linkTo(topImage.end)
            },
            onClick = {
TODO
 }) {

            Icon(Icons.Default.Add,contentDescription = "asd",tint = Color.Yellow)
        }



        Text("Welcome to Native Developer",style = TextStyle(color = Color.Black,fontSize = 24.sp),modifier =  Modifier.constrainAs(text){

            centerHorizontallyTo(card)
            top.linkTo(addButton.bottom)
        })


        Icon(Icons.Outlined.ThumbUp,contentDescription = "asd",tint = Color.Black,modifier = Modifier
            .constrainAs(thumIcon) {
                centerTo(topImage)
            }
            .height(50.dp)
            .width(50.dp))


    }
}

@ExperimentalMaterialApi
@Composable
fun DefaultPreview() {
    ComposeLayoutsTheme {
        scaffoldExample()
    }
}


@ExperimentalMaterialApi
@Composable
fun scaffoldExample() {

    Scaffold(
        topBar = { TopAppBar(
            title = {Text("I am Scaffold")},
            actions = {Icon(Icons.Outlined.Settings,contentDescription = "")}
        )},

        bottomBar ={
            BottomNavigation(
                backgroundColor = Color.White,

                ) {
                BottomNavigationItem(selected = true, onClick = {  },label = {Text("Home")},icon = {
                    Icon(Icons.Outlined.Home,contentDescription = "home")
                })
                BottomNavigationItem(selected = false, onClick = {  },label = {Text("Settings")},icon = {
                    Icon(Icons.Outlined.Settings,contentDescription = "home")
                })
            }
        },

        drawerContent = {
            Surface(modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(.4f),color = Color.Yellow) {

            }

            ListItem(
                text = {Text("Home")},
                icon = {Icon(Icons.Outlined.Home,contentDescription = "home")}
            )

            ListItem(
                text = {Text("Settings")},
                icon = {Icon(Icons.Outlined.Settings,contentDescription = "settings")}
            )
        },
        content = {bodyContent()}
    )

}

@ExperimentalMaterialApi
@Composable
fun bodyContent(){
    Column(
        modifier =Modifier.padding(horizontal = 10.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ){




        Text("Items")
        for(i in 1..5)
        {

            Card(){
                ListItem(
                    text = {Text(" Hello i am list Item")},
                    icon = {
                        Box(modifier = Modifier
                            .fillMaxSize()
                            .clip(
                                shape = CircleShape,
                            )
                            .background(color = Color.Yellow),
                        contentAlignment = Alignment.Center){
                            Text(i.toString())
                        }
                           },
                    secondaryText = {Text(" i am secondary text")},
                )

            }
        }
    }
}

@Composable
fun plantUiScreen(){


    ConstraintLayout(modifier = Modifier
        .fillMaxSize()
        .background(color = colorResource(id = R.color.backgroundColor))) {
        val (logoBar,topBar,search,slide1,itemSlide1,slide2,itemSlide2,bottomBar) = createRefs()


        Surface(modifier = Modifier
            .clip(
                shape = RoundedCornerShape(
                    bottomEnd = 50.dp, bottomStart = 50.dp
                )
            )
            .constrainAs(topBar) {
                top.linkTo(parent.top)
            }
            .fillMaxWidth()
            .fillMaxHeight(.25f),color = colorResource(id = R.color.greenColor)) {



        }


            Row(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .fillMaxWidth()
                    .constrainAs(logoBar) {
                        centerTo(topBar)
                    },
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){


                Text("UiShopy",style = TextStyle(color = Color.White,fontSize = 30.sp))
                Image(modifier = Modifier
                    .height(50.dp)
                    .width(50.dp),painter = painterResource(id = R.drawable.logo),contentDescription = "")

            }
        Box(modifier = Modifier
            .padding(horizontal = 30.dp)
            .clip(
                shape = RoundedCornerShape(
                    20.dp
                )
            )
            .constrainAs(search) {
                top.linkTo(topBar.bottom, margin = (-30).dp)
            }
            .fillMaxWidth()
            .height(55.dp)
            .background(colorResource(id = R.color.white)),contentAlignment = Alignment.Center) {



            Row(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                Icon(Icons.Outlined.Search,contentDescription = "")
                Text("Search")
            }



        }

        Row(
            modifier = Modifier
                .padding(horizontal = 24.dp, vertical = 8.dp)
                .fillMaxWidth()
                .constrainAs(slide1) {
                    top.linkTo(search.bottom, margin = 10.dp)
                },
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            Text("Recommended",style = TextStyle(color = Color.Black,fontWeight = FontWeight.Bold,fontSize = 20.sp))
            Text("see all",style = TextStyle(color = Color.White,fontSize = 18.sp))

        }

        Row(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .fillMaxWidth()
                .constrainAs(itemSlide1) {
                    top.linkTo(slide1.bottom, margin = 4.dp)
                },
            verticalAlignment = Alignment.CenterVertically
        ){

            itemCard(width = 180.dp,R.drawable.image_1,"")
            itemCard(width = 180.dp,R.drawable.image_2,"")
        }
        Row(
            modifier = Modifier
                .padding(horizontal = 24.dp, vertical = 8.dp)
                .fillMaxWidth()
                .constrainAs(slide2) {
                    top.linkTo(itemSlide1.bottom, margin = 10.dp)
                },
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            Text("Featured Plant",style = TextStyle(color = Color.Black,fontWeight = FontWeight.Bold,fontSize = 20.sp))
            Text("see all",style = TextStyle(color = Color.White,fontSize = 18.sp))

        }

        Row(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .fillMaxWidth()
                .constrainAs(itemSlide2) {
                    top.linkTo(slide2.bottom, margin = 4.dp)
                },
            verticalAlignment = Alignment.CenterVertically
        ){

            itemCard(width = 280.dp,R.drawable.bottom_img_1,"")
            itemCard(width = 280.dp,R.drawable.bottom_img_2,"")
        }

        Row(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .fillMaxWidth()
                .constrainAs(bottomBar) {
                    bottom.linkTo(parent.bottom)
                },
            verticalAlignment = Alignment.CenterVertically
        ){


        }



    }

}




@Composable
fun itemCard(width : Dp,image:Int,name : String)
{
    Card(
        Modifier
            .padding(horizontal = 8.dp)
            .clip(
                shape = RoundedCornerShape(
                    10.dp
                )
            )
            .height(200.dp)
            .width(width)
    )
    {
        Column(

        ){
            Image(modifier = Modifier
                .fillMaxWidth()
                .height(150.dp),
                painter = painterResource(id = image), contentDescription ="",contentScale = ContentScale.Crop )


            Row(
                modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 10.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically

            ){
                Text(name,style = TextStyle(color = Color.Black))
                Text("Rs 200",style = TextStyle(color = colorResource(id = R.color.greenColor)))
            }
        }
    }
}


@Composable
fun PaddedComposable() {
    Text(
        "Hello World", modifier = Modifier
            .background(Color.Green)
            .padding(20.dp)
    )
}

@Composable
fun SizedComposable() {
    Box(
        Modifier
            .size(100.dp, 100.dp)
            .background(Color.Red)
    )
}

@Composable
fun FixedSizeComposable() {
    Box(
        Modifier
            .size(90.dp, 150.dp)
            .background(Color.Green)
    ) {
        Box(
            Modifier
                .requiredSize(100.dp, 100.dp)
                .background(Color.Red)
        )
    }
}

@Composable
fun FillSizeComposable() {
    Box(
        Modifier
            .background(Color.Green)
            .size(50.dp)
            .padding(10.dp)
    ) {
        Box(
            Modifier
                .background(Color.Blue)
                .fillMaxSize()
        )
    }
}

@Composable
fun MatchParentSizeComposable() {
    Box {
        Spacer(
            Modifier
                .matchParentSize()
                .background(Color.Green)
        )
        Text("Hello World")
    }
}

@Composable
fun OffsetComposable() {
    Box(
        Modifier
            .background(Color.Yellow)
            .size(width = 150.dp, height = 70.dp)
    ) {
        Text(
            "Layout offset modifier sample",
            Modifier.offset(x = 15.dp, y = 20.dp)
        )
    }
}

@Composable
fun FlexibleComposable() {
    Row(Modifier.width(210.dp)) {
        Box(
            Modifier
                .weight(2f)
                .height(50.dp)
                .background(Color.Blue)
        )
        Box(
            Modifier
                .weight(1f)
                .height(50.dp)
                .background(Color.Red)
        )
    }
}

@Composable
fun WithConstraintsComposable() {
    BoxWithConstraints {
        Text("My minHeight is $minHeight while my maxWidth is $maxWidth")
    }
}



























@Composable
fun ConstraintLayoutContent() {
    var expanded =  remember { mutableStateOf(false) }
    var size =  remember { mutableStateOf(.3f) }


    if(expanded.value)
    {
        size.value = .9f;
    }else{

        size.value = .3f;
    }

    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        // Create references for the composables to constrain
        val (button, text, card, topCard, outlineButton, icon) = createRefs()



        Card(modifier = Modifier
            .fillMaxWidth(.9f)
            .fillMaxHeight(size.value)
            .constrainAs(card) {
                centerTo(parent)
            }) {

        }

        OutlinedButton(

            onClick = {
                      expanded.value = !expanded.value
            },
            // Assign reference "button" to the Button composable
            // and constrain it to the top of the ConstraintLayout
            modifier = Modifier.constrainAs(outlineButton) {


                bottom.linkTo(card.bottom)
                end.linkTo(card.end)

            }
        ) {
            Text(if(expanded.value)"Delete" else "Collapse")
        }

        // Assign reference "text" to the Text composable
        // and constrain it to the bottom of the Button composable
        Text(
            "Welcome to Compose",
            style = TextStyle(fontSize = 24.sp, color = Color.Black),
            modifier = Modifier.constrainAs(text) {
                top.linkTo(topCard.bottom, margin = 16.dp)
                start.linkTo(card.start, margin = 8.dp)

                end.linkTo(card.end, margin = 8.dp)


            })


        Surface(
            modifier = Modifier
                .constrainAs(
                    topCard
                ) {


                    top.linkTo(card.top)
                    start.linkTo(card.start)
                    end.linkTo(card.end)


                }
                .height(120.dp)
                .fillMaxWidth(.9f)
                .clip(shape = RoundedCornerShape(8.dp)),
            color = Color.Yellow,
        ) {

        }

        Icon(
            Icons.Outlined.ThumbUp,
            contentDescription = "",
            modifier = Modifier
                .width(40.dp)
                .height(40.dp)
                .constrainAs(icon) {
                    centerTo(topCard)
                })

        FloatingActionButton(
            onClick = {
 Do something
 },
            // Assign reference "button" to the Button composable
            // and constrain it to the top of the ConstraintLayout
            modifier = Modifier.constrainAs(button) {

                top.linkTo(topCard.bottom,margin = (-20).dp)
                end.linkTo(topCard.end)

            },
            backgroundColor = Color.Black
        ) {
            Icon(
                imageVector = Icons.Outlined.Add,

                contentDescription = "",
                modifier = Modifier
                    .width(40.dp)
                    .height(40.dp)
                    .constrainAs(icon) {
                        centerTo(topCard)
                    },

                        tint = Color.Yellow

            )
        }

    }

}

data class ShoeModel(val price: String, val image: Int, val color: Int)



