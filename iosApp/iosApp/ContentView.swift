import SwiftUI
import shared

@available(iOS 16.0, *)
struct ContentView: View {
	@State private var shouldOpenAbout = false

	var body: some View {
		NavigationStack{
			ArticlesScreen(viewModel: .init())
				.toolbar {
				ToolbarItem {
					Button {
						shouldOpenAbout = true
					} label: {
						Label("About", systemImage: "info.circle").labelStyle(.titleAndIcon)
					}
					.popover(isPresented: $shouldOpenAbout) {
						AboutScreen()
					}
				}
			}
		}
	}
}

@available(iOS 16.0, *)
struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}