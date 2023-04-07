# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

# 防止 TypeUtil 反射获取外部类实例引用报错
-keepclassmembers class * {* this$*;}
# 防止 BindingUtil 反射创建 ViewDataBinding 报错
-keepclassmembers class * extends androidx.databinding.ViewDataBinding {* inflate(...);}
## Bean
-keep class com.rick.openaidemo.bean.**{*;}

-dontwarn com.tencent.bugly.**
-keep public class com.tencent.bugly.**{*;}

-keepattributes InnerClasses
-if @kotlinx.serialization.Serializable class
com.aallam.openai.api.**
{
    static **$* *;
}
-keepnames class <1>$$serializer {
    static <1>$$serializer INSTANCE;
}