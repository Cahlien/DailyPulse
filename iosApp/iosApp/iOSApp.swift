import SwiftUI
import shared

@main
struct iOSApp: App {

	init() {
		KoinInitializerKt.doInitKoin()
	}
	var body: some Scene {
		WindowGroup {
			if #available(iOS 16.0, *) {
				ContentView()
			} else {
				// Fallback on earlier versions
			}
		}
	}
}