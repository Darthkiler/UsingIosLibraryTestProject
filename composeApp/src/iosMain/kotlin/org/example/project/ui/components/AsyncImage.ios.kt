package org.example.project.ui.components


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.UIKitView
import cocoapods.SDWebImage.SDWebImageManager
import cocoapods.SDWebImage.SDWebImageOptions
import kotlinx.cinterop.ExperimentalForeignApi
import platform.CoreGraphics.CGRectMake
import platform.UIKit.UIImageView

@OptIn(ExperimentalForeignApi::class)
@Composable
actual fun AsyncImage(modifier: Modifier, url: String) {
    val imageView = UIImageView(CGRectMake(0.0, 0.0, 200.0, 300.0))
    imageView.clipsToBounds = true

    SDWebImageManager.sharedManager.loadImageWithURL(
        url = platform.Foundation.NSURL(string = url),
        options = SDWebImageOptions.MIN_VALUE,
        progress = null
    ) { image, _, _, _, _, _ ->
        if (image != null) {
            imageView.image = image
        }
    }

    UIKitView(factory = { imageView }, modifier = modifier)
}