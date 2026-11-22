# Keep Supabase / kotlinx serialization models
-keepattributes *Annotation*, InnerClasses
-dontwarn kotlinx.serialization.**
-keep,includedescriptorclasses class com.noonechat.app.**$$serializer { *; }
-keepclassmembers class com.noonechat.app.** {
    *** Companion;
}
