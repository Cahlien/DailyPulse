import Foundation
import SwiftUI

@available(iOS 16.0, *)
struct AboutScreen: View {
    @Environment(\.dismiss)
    private var dismiss

    var body: some View {
        NavigationStack {
            AboutListView()
                .navigationTitle("About Device")
                .toolbar {
                    ToolbarItem(placement: .primaryAction) {
                        Button {
                            dismiss()
                        } label: {
                            Text("Done")
                                .bold()
                        }
                    }
            }
        }
    }
}

@available(iOS 16.0, *)
#Preview {
    AboutScreen()
}