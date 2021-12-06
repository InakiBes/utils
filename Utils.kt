/********** Dates & Time *************/
//Current Date Milliseconds
fun getCurrentDate():Long{
    return Date().time
}

//Current Date String
//formatDate example: "yyyy-MM-dd"
fun getCurrentDateString(formatDate:String):String {
    return dateLongToTextDate(getCurrentDate(), formatDate) 
}

//Current Time String
//formatTime example: "HH:mm"
fun getCurrentTimeString(formatTime:String):String{
    val formatter = DateTimeFormatter.ofPattern(formatTime) 
    val currentDateTime = LocalDateTime.now()
    return currentDateTime.format(formatter)
}

//Current Time and Date String
//formatTimeAndDate example: "yyyy-MM-dd'T'HH:mm:ss'+02:00'"
fun getCurrentDateAndTime(formatTimeAndDate:String):String{
    val currentDateTime = LocalDateTime.now()
    val formatter = DateTimeFormatter.ofPattern(formatTimeAndDate)
    return currentDateTime.format(formatter).toString()
}

//Text Date to Date long
//formatDate example: "yyyy-MM-dd'T'HH:mm:ss"
fun dateTextToDateLong(stringDate:String, formatDate:String): Long {
    var milliseconds : Long= 0
    val format = SimpleDateFormat(formatDate) 
    try {
        val date = format.parse(stringDate)
        milliseconds = date.time
    } catch (e: ParseException) {
        e.printStackTrace()
    }

    return milliseconds
}

//Date long to Text Date
//formatDate example: "yyyy-MM-dd'T'HH:mm:ss"
fun dateLongToTextDate(long:Long, format: String):String{
    var dateString = ""
    val dateFormat = SimpleDateFormat(format) 
    try {
        dateString = dateFormat.format(Date(long))
    } catch (ex: ParseException) {
        Log.d("tag", ex.getLocalizedMessage())
    }
    return dateString
}



/********* EMAILS & TEXT ********************/
//Validate email
fun isEmailValid(email: String): Boolean {
    return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
}

//Validate email Gmail
fun isGmailEmail(email: String) =
    Pattern.compile(
        "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                "\\@" +
                "gmail.com"
    ).matcher(email).matches()


/*********** Clean Text **************/
fun cleanText(str:String):String{
    val pattern1 = "\\[.*?\\]".toRegex()
    val pattern2 = "\\<.*?\\>".toRegex()
    val pattern3 = "&lt;div.*?&lt;div".toRegex()

    return str.replace(pattern1, "").replace(pattern2, "").replace(pattern3, "")
}

/************ Normalize URL */
fun normalizeUrlIfRequired(content: Any): Any =
    when (content) {
        is String -> if (content.startsWith("http")) content.replace("\\", "/") else "http://$content".replace("\\", "/")
        else -> content
}


/******** IMAGES *********************/
//Set image using Glide
fun setImage(context: Context, content: Any, into: ImageView) {
    Glide.with(context)
        .load(normalizeUrlIfRequired(content))
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .error(R.drawable.not_image)
        .into(into)
}

private fun normalizeUrlIfRequired(content: Any): Any =
    when (content) {
        is String -> if (content.startsWith("http")) content.replace("\\", "/") else "http://$content".replace("\\", "/")
        else -> content
}