import Foundation
import SwiftUI

@available(iOS 16.0, *)
struct AboutScreen: View {
    var body: some View {
        NavigationStack {
            AboutListView()
                .navigationTitle("About Device")
        }
    }
}

@available(iOS 16.0, *)
#Preview {
    if #available(iOS 16.0, *) {
        AboutScreen()
    } else {
        // Fallback on earlier versions
    }
}