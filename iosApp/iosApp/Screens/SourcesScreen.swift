import Foundation

import SwiftUI
import shared

@available(iOS 16.0, *)
extension SourcesScreen {
    @MainActor
    class SourcesViewModelWrapper: ObservableObject {
        init() {
            viewModel = SourcesInjector().sourcesViewModel
            sourcesState = viewModel.sourcesState.value
        }

        let viewModel: SourcesViewModel

        @Published var sourcesState: SourcesState

        func startObserving() {
            Task {
                for await sourcesS in viewModel.sourcesState {
                    self.sourcesState = sourcesS
                }
            }
        }
    }
}

@available(iOS 16.0, *)
struct SourcesScreen: View {
    @Environment(\.dismiss)
    private var dismiss
    @ObservedObject private(set) var viewModel: SourcesScreen.SourcesViewModelWrapper

    var body: some View {
        NavigationStack {
            VStack {
                if let error = viewModel.sourcesState.error {
                    ErrorMessage(message: error)
                }

                if viewModel.sourcesState.loading {
                    Loader()
                }

                if !viewModel.sourcesState.sources.isEmpty {
                    ScrollView {
                        LazyVStack(spacing: 10) {
                            ForEach(viewModel.sourcesState.sources, id: \.self) {
                                source in SourceItemView(name: source.name, desc: source.desc, origin: source.origin)
                            }
                        }
                    }
                }
            }.onAppear{
                self.viewModel.startObserving()
            }
                .navigationTitle("Sources")
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

struct SourceItemView: View {
    let name: String
    let desc: String
    let origin: String

    var body: some View {
        VStack(alignment: .leading, spacing: 8) {
            Text(name)
                .font(.title)
                .fontWeight(.bold)
            Text(desc)
            if #available(iOS 15.0, *) {
                Text(origin).frame(maxWidth: .infinity, alignment: .trailing).foregroundStyle(.gray)
            } else {
                // Fallback on earlier versions
            }
        }
        .padding(16)
    }
}