import SwiftUI
import shared

struct ContentView : View {
    var body: some View {
        ComposeView().ignoresSafeArea(.keyboard)
    }
}

struct ComposeView : UIViewControllerRepresentable {
    func updateUIViewController(_ uiViewController: UIViewControllerType, context: Context) {}

    func makeUIViewController(context: Context) -> some UIViewController {
        MainIOSKt.MainViewController()
    }
}